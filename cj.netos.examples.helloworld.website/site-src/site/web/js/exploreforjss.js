$(document).ready(function () {
    $('#test-button').on('click', function () {
        var name = $('#name').val();
        if(name===''){
            alert('The name is null.');
            return;
        }
        $.get('./exploreforjss.html?action=getName&name=' + name, function (data) {
            $('#response').find('>p').remove();
            $('#response').append('<p>'+data+'</p>');
        });
    })
});