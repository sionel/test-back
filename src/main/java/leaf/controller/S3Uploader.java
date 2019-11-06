package leaf.controller;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import org.springframework.stereotype.Component;

@Component
public class S3Uploader {

    private final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();

    public List<S3ObjectSummary> getAllObject(String bucketName) {
        final String log = "\n" + "Get all objects in dz-leaf..";
        System.out.println(log);

        ListObjectsV2Result result = s3.listObjectsV2(bucketName);
        // if (result.getObjectSummaries().isEmpty()) {
        // throw new RuntimeException(bucketName + " Bucket이 비어 있습니다.");
        // }
        return result.getObjectSummaries();
    }

    public void putObject(String bucketName, String filePath) {
        final String log = "\n" + "Put in " + bucketName + "..";
        System.out.println(log);

        String keyName = Paths.get(filePath).getFileName().toString();
        try {
            s3.putObject(bucketName, keyName, new File(filePath));
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
        System.out.println(keyName + " 파일 업로드 완료");
    }

    public void deleteObject(String bucketName, String objectKey) {
        final String log = "\n" + "Delete " + objectKey + " in " + bucketName + "..";
        System.out.println(log);

        try {
            s3.deleteObject(bucketName, objectKey);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
        System.out.println(objectKey + " 파일 삭제 완료");
    }

}
