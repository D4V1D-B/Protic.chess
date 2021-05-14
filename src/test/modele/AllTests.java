package test.modele;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ CavalierTest.class, FouTest.class, PionTest.class,
		ReineTest.class, RoiTest.class,TourTest.class,MouvementTest.class,MoveTest.class, PlateauTest.class})
public class AllTests {

}
