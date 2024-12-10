package auton;

import autoutil.AutoFramework;

public class AutoTestNew extends AutoFramework {


    @Override
    public void initialize() {
        testInit(this);


    }
//    AutoModule Test1 = new AutoModule(
//            testpart.Stage1(5)
//    );

//    AutoModule Test2 = new AutoModule(
//            testpart.Stage2(5)
//    );

//    AutoModule Test3 = new AutoModule(
//            testpart.Stage3(5)
//    );

    public static void testInit(AutoFramework auto) {
        auto.scan(true, "blue", "right");
    }

    @Override
    public void define() {
//        addAutoModule(Test1);
//        customCase(() -> {
//
//        }, () -> {
//
//
//        }, () -> {
//
//        });
    }
}
