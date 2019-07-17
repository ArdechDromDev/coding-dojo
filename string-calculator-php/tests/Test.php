<?php

namespace Test;

use PHPUnit\Framework\TestCase;

require "../index.php";

class Test extends TestCase
{
    public function testIfInteger()
    {
        $integer = expression('8');
        $this->assertEquals(8, $integer);
    }

    public function testIfIntegerIsSeven()
    {
        $integer = expression('7');
        $this->assertEquals(7, $integer);
    }

    public function testOnePlusOneIsEqualToTwo()
    {
        $integer = expression('1+1');
        $this->assertEquals(2, $integer);
    }


    public function testOnePlusOnePlusOneIsEqualToThree()
    {
        $integer = expression('1+1+1');
        $this->assertEquals(3, $integer);
    }


    public function testOnePlusOneMinusOneIsEqualToOne()
    {
        $integer = expression('1+1-1');
        $this->assertEquals(1, $integer);

    }

    public function testTwoMultiplyThreeIsEqualToSix()
    {
        $integer = expression('2*3');
        $this->assertEquals(6, $integer);
    }

    public function testOnePlusTwoMultiplyThreeIsEqualToSeven()
    {
        $integer = expression('1+2*3');
        $this->assertEquals(7, $integer);
    }

    public function testTwoMultiplyByTwoPlusThreeIsEqualToSeven()
    {
        $integer = expression('2*2+3');
        $this->assertEquals(7, $integer);
    }

    public function testThreePlusTwoMultiplyByTwoPlusThreeIsEqualToSeven()
    {
        $integer = expression('3+2*2+3');
        $this->assertEquals(10, $integer);
    }
}