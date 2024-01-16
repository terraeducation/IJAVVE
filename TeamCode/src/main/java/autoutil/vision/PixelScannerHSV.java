package autoutil.vision;

import static autoutil.vision.Scanner.BLUE;
import static autoutil.vision.Scanner.GREEN;
import static autoutil.vision.Scanner.RED;
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

@Autonomous(name = "PixelHSV", group = "auto")
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
            int lowerValue = 200;
            int space = 20;

            int topx = 750; int topy = 585; int width = 150; int height = 25;
//            Mat edges = new Mat();
            Rect rect1 = new Rect(topx, topy, width, height);
            Rect rect2 = new Rect(topx, topy + height, width, height);
            Rect rect3 = new Rect(topx, topy + height * 2, width, height);
            Rect rect4 = new Rect(topx, topy + height * 3, width, height);
            Rect rect5 = new Rect(topx, topy + height * 4, width, height);

            Rect rightRect1 = new Rect(topx - width / 2 - space, topy, width / 2, height);
            Rect rightRect2 = new Rect(topx - width / 2 - space, topy + height, width / 2, height);
            Rect rightRect3 = new Rect(topx - width / 2 - space, topy + height * 2, width / 2, height);
            Rect rightRect4 = new Rect(topx - width / 2 - space, topy + height * 3, width / 2, height);
            Rect rightRect5 = new Rect(topx - width / 2 - space, topy + height * 4, width / 2, height);

            Rect leftRect1 = new Rect(topx + width + space, topy, width / 2, height);
            Rect leftRect2 = new Rect(topx + width + space, topy + height, width / 2, height);
            Rect leftRect3 = new Rect(topx + width + space, topy + height * 2, width / 2, height);
            Rect leftRect4 = new Rect(topx + width + space, topy + height * 3, width / 2, height);
            Rect leftRect5 = new Rect(topx + width + space, topy + height * 4, width / 2, height);

            Imgproc.rectangle(input, rect1, GREEN, 3);
            Imgproc.rectangle(input, rect2, GREEN, 3);
            Imgproc.rectangle(input, rect3, GREEN, 3);
            Imgproc.rectangle(input, rect4, GREEN, 3);
            Imgproc.rectangle(input, rect5, GREEN, 3);

            Imgproc.rectangle(input, rightRect1, RED, 3);
            Imgproc.rectangle(input, rightRect2, RED, 3);
            Imgproc.rectangle(input, rightRect3, RED, 3);
            Imgproc.rectangle(input, rightRect4, RED, 3);
            Imgproc.rectangle(input, rightRect5, RED, 3);

            Imgproc.rectangle(input, leftRect1, BLUE, 3);
            Imgproc.rectangle(input, leftRect2, BLUE, 3);
            Imgproc.rectangle(input, leftRect3, BLUE, 3);
            Imgproc.rectangle(input, leftRect4, BLUE, 3);
            Imgproc.rectangle(input, leftRect5, BLUE, 3);

            Mat matRect1 = mat.submat(rect1);
            Mat matRect2 = mat.submat(rect2);
            Mat matRect3 = mat.submat(rect3);
            Mat matRect4 = mat.submat(rect4);
            Mat matRect5 = mat.submat(rect5);

            Mat matRightRect1 = mat.submat(rightRect1);
            Mat matRightRect2 = mat.submat(rightRect2);
            Mat matRightRect3 = mat.submat(rightRect3);
            Mat matRightRect4 = mat.submat(rightRect4);
            Mat matRightRect5 = mat.submat(rightRect5);

            Mat matLeftRect1 = mat.submat(leftRect1);
            Mat matLeftRect2 = mat.submat(leftRect2);
            Mat matLeftRect3 = mat.submat(leftRect3);
            Mat matleftRect4 = mat.submat(leftRect4);
            Mat matLeftRect5 = mat.submat(leftRect5);

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

            Scalar rightv1 = Core.mean(matRightRect1);
            Scalar rightv2 = Core.mean(matRightRect2);
            Scalar rightv3 = Core.mean(matRightRect3);
            Scalar rightv4 = Core.mean(matRightRect4);
            Scalar rightv5 = Core.mean(matRightRect5);

            Scalar leftv1 = Core.mean(matLeftRect1);
            Scalar leftv2 = Core.mean(matLeftRect2);
            Scalar leftv3 = Core.mean(matLeftRect3);
            Scalar leftv4 = Core.mean(matleftRect4);
            Scalar leftv5 = Core.mean(matLeftRect5);

            double v1Avg = v1.val[2];
            double v2Avg = v2.val[2];
            double v3Avg = v3.val[2];
            double v4Avg = v4.val[2];
            double v5Avg = v5.val[2];

            double rightv1Avg = rightv1.val[2];
            double rightv2Avg = rightv2.val[2];
            double rightv3Avg = rightv3.val[2];
            double rightv4Avg = rightv4.val[2];
            double rightv5Avg = rightv5.val[2];

            double leftv1Avg = leftv1.val[2];
            double leftv2Avg = leftv2.val[2];
            double leftv3Avg = leftv3.val[2];
            double leftv4Avg = leftv4.val[2];
            double leftv5Avg = leftv5.val[2];




//            mask1.copyTo(input);

            double[] values = new double[] {v1Avg, v2Avg, v3Avg, v4Avg, v5Avg};

            for (int i = 0; i < values.length; i++) {
                telemetry.addData("Center " + String.valueOf(i), values[i]);
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
