/*global $ */

$(function () {
    "use strict"
    const maze = $("#maze");
    const start = $('#start');
    const end = $('#end');
    const boundary = $('.boundary');
    const status = $('#status');
    let hitWall = false;

    const loss = function () {
        if (!hitWall) {
            boundary.addClass("youlose");
            hitWall = true;
        }
    }
    start.click(function () {
        console.log("start");
        // clear
        hitWall = false;
        if (boundary.hasClass("youlose")) {
            boundary.removeClass("youlose");
        }
        status.text("Moving ... ");

        // end
        end.click(function () {
                if (hitWall) {
                    status.text("Sorry, you lost. :(");
                } else {
                    status.text("You win! :)");
                }
                //TODO remove boundary.mouseover
            }
        );

        // loss
        boundary.mouseover(loss);
        maze.mouseleave(loss);

    });
});