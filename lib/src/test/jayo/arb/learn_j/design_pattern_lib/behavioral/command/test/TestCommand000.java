package jayo.arb.learn_j.design_pattern_lib.behavioral.command.test;

import jayo.arb.learn_j.design_pattern_lib.behavioral.command.Button;
import jayo.arb.learn_j.design_pattern_lib.behavioral.command.Command;
import jayo.arb.learn_j.design_pattern_lib.behavioral.command.IDevice;
import jayo.arb.learn_j.design_pattern_lib.behavioral.command.Television;
import jayo.arb.learn_j.design_pattern_lib.behavioral.command.TurnOff;
import jayo.arb.learn_j.design_pattern_lib.behavioral.command.TurnOn;
import jayo.arb.learn_j.design_pattern_lib.behavioral.command.TurnVolDown;
import jayo.arb.learn_j.design_pattern_lib.behavioral.command.TurnVolUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCommand000 {

    public static final String CLASS_NAME = "TestCommand";

    private final IDevice tv;

    private TestCommand000() {
        this.tv = new Television();
    }

    @Test
    @Order( 0 )
    @DisplayName( "testPowerOnOff@" + CLASS_NAME )
    public void testPowerOnOff() {

        //REM: POWER
        Assertions.assertFalse( this.tv.isOn() );

        Command tvCmd = new TurnOn( this.tv );
        Button btn = new Button( tvCmd );

        Assertions.assertFalse( this.tv.isOn() );

        btn.press();
        Assertions.assertTrue( this.tv.isOn() );

        tvCmd = new TurnOff( this.tv );
        btn.setCommand( tvCmd );
        btn.press();
        Assertions.assertFalse( this.tv.isOn() );


    }

    @Test
    @Order( 1 )
    @DisplayName( "testVolume@" + CLASS_NAME )
    public void testVolume() {

        //REM: VOLUME
        Assertions.assertEquals( 0, this.tv.getVolume() );

        Command tvCmd = new TurnVolDown( this.tv );
        Button btn = new Button( tvCmd );

        Assertions.assertEquals( 0, this.tv.getVolume() );

        tvCmd = new TurnVolUp( this.tv );
        btn.setCommand( tvCmd );
        btn.press();
        Assertions.assertEquals( 1, this.tv.getVolume() );
        btn.press();
        btn.press();
        btn.press();
        Assertions.assertEquals( 4, this.tv.getVolume() );
        for( int i = 0; i < 901; ++i )
            btn.press();
        Assertions.assertEquals( 100, this.tv.getVolume() );

        tvCmd = new TurnVolDown( this.tv );
        btn.setCommand( tvCmd );
        btn.press();
        Assertions.assertEquals( 99, this.tv.getVolume() );
        for( int i = 0; i < 901; ++i )
            btn.press();
        Assertions.assertEquals( 0, this.tv.getVolume() );

    }
}
