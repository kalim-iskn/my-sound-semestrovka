$(document).on('click', '.links-in-new-tab a', function(){
    let attr = $(this).attr('data-pjax');
    if(!(typeof attr !== typeof undefined && attr !== false)) {
        window.open($(this).attr("href"))
        return false
    }
})

$(document).pjax('a[data-pjax]', '#pjax-container');
$.pjax.defaults.timeout = 5000;

$(document).on('submit', 'form', function (event) {
    $.pjax.submit(event, '#pjax-container')
})
