/*jslint browser */
/*global window ANIMATIONS*/

(function () {
    "use strict";
    window.onload = function () {
        const text_area = document.getElementById("text-area");
        const startButton = document.getElementById("start");
        const stopButton = document.getElementById("stop");
        const animation = document.getElementById("animation");
        const fontsize = document.getElementById("fontsize");
        const turbo = document.getElementById("turbo");

        text_area.className = "Medium";
        let old_text_value;
        let timer_animation;
        let frameNum = 0;

        animation.onchange = function () {
            text_area.value = ANIMATIONS[animation.value];
        };

        function animationFunc(split) {
            text_area.value = split[frameNum];
            frameNum = frameNum + 1;
            if (split.length === frameNum) {
                frameNum = 0;
            }
        }

        startButton.onclick = function () {
            document.getElementById("start").disabled = true;
            document.getElementById("animation").disabled = true;
            document.getElementById("stop").disabled = false;

            old_text_value = text_area.value;
            let split = text_area.value.split("=====\n");
            frameNum = 0;
            if (timer_animation !== null) {
                clearInterval(timer_animation);
            }
            timer_animation = setInterval(function () {
                animationFunc(split);
            }, (turbo.checked ? 50 : 250));
        };

        stopButton.onclick = function () {
            document.getElementById("start").disabled = false;
            document.getElementById("stop").disabled = true;
            document.getElementById("animation").disabled = false;

            clearInterval(timer_animation);
            timer_animation = null;
            text_area.value = old_text_value;
        };

        fontsize.onchange = function () {
            text_area.className = fontsize.value;
        };

        turbo.onchange = function () {
            if (!timer_animation || !old_text_value) {
                return;
            }
            let split = old_text_value.split("=====\n");
            clearInterval(timer_animation);

            timer_animation = setInterval(function () {
                animationFunc(split);
            }, (turbo.checked ? 50 : 250));
        };
    };
})();

