/*
 * Author: Brendan Le Foll <brendan.le.foll@intel.com>
 * Copyright (c) 2014 Intel Corporation.
 * Author: Jakub Kramarz <jkramarz@virtuslab.com>
 * Copyright (c) 2015 VirtusLab
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import mraa.Pwm;

public class CyclePwm3 {
    static {
        try {
            System.loadLibrary("mraajava");
        } catch (UnsatisfiedLinkError e) {
            System.err.println(
                    "Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" +
                            e);
            System.exit(1);
        }
    }
    public static void main(String argv[]) throws InterruptedException {
        //! [Interesting]
        Pwm pwm = new mraa.Pwm(3);
        pwm.period_us(200);
        pwm.enable(true);

        float value = 0;
        for (int i = 100; i > 0; --i) {
            value += 0.01;
            pwm.write(value);
            Thread.sleep(50);
            if (value >= 1) {
                value = 0;
            }
        }
        //! [Interesting]
    }
}
