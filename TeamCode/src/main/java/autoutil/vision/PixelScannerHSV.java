package autoutil.vision;

import static autoutil.vision.Scanner.GREEN;
import static global.General.log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.awt.font.ImageGraphicAttribute;
import java.util.ArrayList;
import java.util.List;

//@Autonomous(name = "PixelHSV", group = "auto")
public class PixelScannerHSV extends LinearOpMode {
    @Override
    public void runOpMode() {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "ecam");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        // With live preview
        OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode)
            {
                log.show("CAN'T BE OPENED");
            }


        });
        telemetry.addLine("Starting");
        camera.setPipeline(new Scan());
        telemetry.update();
//        ScannerPipeline scannerPipeline = new ScannerPipeline();
//        scannerPipeline.message();
        waitForStart();
    }

    class Scan extends OpenCvPipeline {
        Mat mat = new Mat();
        @Override
        public Mat processFrame(Mat input) {
            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
//            Rect rect_crop = new Rect(50, 0, 150, 300);
//            Mat crop = mat.submat(rect_crop);
////            Scalar lowerBound = new Scalar(0, 0, 255 - 15);
////            Scalar upperBound = new Scalar(255, 15, 255);
////            Scalar lowerBound = new Scalar(0, 0, 0);
////            Scalar upperBound = new Scalar(0, 0, 255);
////            Scalar lowerBound = new Scalar(75, 0, 99);
////            Scalar upperBound = new Scalar(179, 62, 255);
            Scalar lowerBound = new Scalar(0, 0, 168);
            Scalar upperBound = new Scalar(172, 111, 255);
            int lowerValue = 150;

            int topx = 400; int topy = 300; int width = 300; int height = 60;
//            Mat edges = new Mat();
            Rect rect1 = new Rect(topx, topy, width, height);
            Rect rect2 = new Rect(topx, topy + height, width, height);
            Rect rect3 = new Rect(topx, topy + height * 2, width, height);
            Rect rect4 = new Rect(topx, topy + height * 3, width, height);
            Rect rect5 = new Rect(topx, topy + height * 4, width, height);

            Mat mask1 = new Mat();
            Mat mask2 = new Mat();
            Mat mask3 = new Mat();
            Mat mask4 = new Mat();
            Mat mask5 = new Mat();

            Imgproc.rectangle(input, rect1, GREEN, 3);
            Imgproc.rectangle(input, rect2, GREEN, 3);
            Imgproc.rectangle(input, rect3, GREEN, 3);
            Imgproc.rectangle(input, rect4, GREEN, 3);
            Imgproc.rectangle(input, rect5, GREEN, 3);


            Mat matRect1 = mat.submat(rect1);
            Core.inRange(matRect1, lowerBound, upperBound, mask1);
            Mat matRect2 = mat.submat(rect2);
            Core.inRange(matRect2, lowerBound, upperBound, mask2);
            Mat matRect3 = mat.submat(rect3);
            Core.inRange(matRect3, lowerBound, upperBound, mask3);
            Mat matRect4 = mat.submat(rect4);
            Core.inRange(matRect4, lowerBound, upperBound, mask4);
            Mat matRect5 = mat.submat(rect5);
            Core.inRange(matRect5, lowerBound, upperBound, mask5);

//            Core.extractChannel(matRect1, matRect1, 0);
//            Core.extractChannel(matRect2, matRect2, 0);
//            Core.extractChannel(matRect3, matRect3, 0);
//            Core.extractChannel(matRect4, matRect4, 0);
//            Core.extractChannel(matRect5, matRect5, 0);
//            Core.extractChannel(matRect6, matRect6, 0);

            Scalar v1 = Core.mean(matRect1);
            Scalar v2 = Core.mean(matRect2);
            Scalar v3 = Core.mean(matRect3);
            Scalar v4 = Core.mean(matRect4);
            Scalar v5 = Core.mean(matRect5);

            double v1Avg = v1.val[2];
            double v2Avg = v2.val[2];
            double v3Avg = v3.val[2];
            double v4Avg = v4.val[2];
            double v5Avg = v5.val[2];




//            mask1.copyTo(input);

            mask1.release();
            mask2.release();
            mask3.release();
            mask4.release();
            mask5.release();

            double[] values = new double[] {v1Avg, v2Avg, v3Avg, v4Avg, v5Avg};

            for (int i = 0; i < values.length; i++) {
                telemetry.addData(String.valueOf(i), values[i]);
            }

            int p_count = 0;

            for (double val: values) {
                if (val >= lowerValue) {
                    p_count += 1;
                }
            }

            telemetry.addData("Pixel count", p_count);

            telemetry.update();

//            edges.release();
//            crop.copyTo(input);
//            input.release();
            return input;
        }
    }
}
