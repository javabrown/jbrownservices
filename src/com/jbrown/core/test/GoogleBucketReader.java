package com.jbrown.core.test;
 
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsInputChannel;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;

import org.apache.commons.io.IOUtils;
 
/**
 * Class to reads and writes to its Google Cloud Storage bucket.
 * 
 * @author rkhan
 */
@SuppressWarnings("serial")
public class GoogleBucketReader {
 
  /**
   * This is where backoff parameters are configured. Here it is aggressively retrying with
   * backoff, up to 10 times but taking no more that 15 seconds total to do so.
   */
  private final GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
      .initialRetryDelayMillis(10)
      .retryMaxAttempts(10)
      .totalRetryPeriodMillis(15000)
      .build());

  /**Used below to determine the size of chucks to read in. Should be > 1kb and < 10MB */
  private static final int BUFFER_SIZE = 2 * 1024 * 1024;

  /**
   * Retrieves a file from GCS and returns it in the http response.
   * If the request path is /gcs/Foo/Bar this will be interpreted as
   * a request to read the GCS file named Bar in the bucket Foo.
   * @throws IOException 
   */
  public byte[] readFromBucket(String bucketName, String objectName) throws IOException {
    GcsFilename fileName = new GcsFilename(bucketName, objectName);

    // if (SERVE_USING_BLOBSTORE_API) {
    // BlobstoreService blobstoreService =
    // BlobstoreServiceFactory.getBlobstoreService();
    // BlobKey blobKey = blobstoreService.createGsBlobKey(
    // "/gs/" + fileName.getBucketName() + "/" + fileName.getObjectName());
    // blobstoreService.serve(blobKey, resp);
    // }

    GcsInputChannel readChannel = gcsService.openPrefetchingReadChannel(
        fileName, 0, BUFFER_SIZE);

    InputStream input = Channels.newInputStream(readChannel);

    byte[] bytes = read(input);

    return bytes;
  }
 
  public void writeToBucket(String bucketName, String fileName, byte[] data) throws IOException {
    GcsFilename gcsFileName = new GcsFilename(bucketName, fileName);

    GcsOutputChannel outputChannel =
        gcsService.createOrReplace(gcsFileName, GcsFileOptions.getDefaultInstance());
    OutputStream output = Channels.newOutputStream(outputChannel);
    
    this.write(output, data);
  }

  private byte[] read(InputStream input) throws IOException {
    byte[] data = new byte[0];

    try {
      data = IOUtils.toByteArray(input);
    } finally {
      input.close();
    }

    return data;
  }
  
  private void write(OutputStream output, byte[] data) throws IOException {
    try {
      output.write(data);
    } finally {
      output.close();
    }
  }
  
//  public static void main(String[] args) throws IOException{
//    GoogleBucketReader g = new GoogleBucketReader();
//    g.readFromBucket("test", "test1");
//    System.out.println("Done");
//  }
}