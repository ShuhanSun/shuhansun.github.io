$(document).ready(function () {

    //Make the circle grow by 10px every 250ms.
    let width = 50;
    let growthAmount = 10;
    let growRate = 250;
    const circle0 = $('.circle');
    //Remove the circle when you click on it.
    circle0.click(function () {
        this.remove();
    });

    growth(circle0, width, growthAmount, growRate);

    //Put 3 text inputs on the screen with labels:width, growth amount, grow rate.Also add a button labeled ‘Start’.
    // When the button is clicked the circle should appear, this time with the specified width, growth size and interval for growing.
    $('#start').click(function () {
        let width = parseInt($('#width').val());
        if (!width) {
            width = 50;
        }
        let growthAmount = parseInt($('#growthAmount').val());
        if (!growthAmount) {
            growthAmount = 10;
        }
        let growRate = parseInt($('#growRate').val());
        if (!growRate) {
            growRate = 250;
        }
        let numberCircles = parseInt($('#numberCircles').val());
        if (!numberCircles) {
            numberCircles = 1;
        }
        for (let i = 0; i < numberCircles; i++) {
            const cid = 'circle' + i;
            const c = $("<div class='circle' id=" + cid + "></div>");
            c.click(function () {
                this.remove();
            });
            $('body').append(c);

            growth(c, width, growthAmount, growRate);
        }

    });

});

function growth(circle, width, growthAmount, growRate) {
    setInterval(function () {
        width += growthAmount;
        circle.css({
            "width": width + "px",
            "height": width + "px",
            "background": "#" + width
        })
    }, growRate);
}