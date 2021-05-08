function modalFormSubmit() {
    let body = $('body');
    body.removeClass('modal-open');
    body.removeAttr('style');
    $('.modal-backdrop').hide();
}
