package org.jdownloader.captcha.v2.solver.imagetyperz;

import org.jdownloader.captcha.v2.challenge.stringcaptcha.BasicCaptchaChallenge;
import org.jdownloader.captcha.v2.challenge.stringcaptcha.CaptchaResponse;

public class ImageTyperzResponse extends CaptchaResponse {

    private String captchaID;

    public String getCaptchaID() {
        return captchaID;
    }

    public ImageTyperzResponse(BasicCaptchaChallenge challenge, ImageTyperzCaptchaSolver ImageTyperzSolver, String id, String text, int priority) {
        super(challenge, ImageTyperzSolver, text, priority);
        this.captchaID = id;
    }

}
