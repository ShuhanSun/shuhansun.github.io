$(function () {
    $("#send").click(updatePosts);
});

function updatePosts() {
    var nameId = $("#nameId").val();

    $.ajax("http://jsonplaceholder.typicode.com/users/" + nameId
    ).done(displayUser).fail(displayUserNotFound);

    $.ajax("http://jsonplaceholder.typicode.com/users/" + nameId + "/posts"
    ).done(displayPosts);
}
function displayUserNotFound(data) {
    $("#user").html(("User not found!"
        + "<br/>"));
}
function displayUser(data) {
    $("#user").html(("User name:" + data.name
        + "<br/> Email:" + data.email
        + "<br/> Address:" + data.address.street + " " + data.address.suite + " " + data.address.city
        + "<br/>"));
}

function displayPosts(data) {
    let postList = "";
    data.forEach((post) => {
        postList += (post.id + ": " + post.title + " <br/> " + post.body + "<br/>");
    });
    $("#postList").html(postList);
}