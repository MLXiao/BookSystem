function previewFile() {
    var $coverImg = $('.cover_img');
    var imgFile = document.querySelector('input[type=file]').files[0];
    var reader = new FileReader();
    reader.onloadend = function () {
        $coverImg.attr('src',reader.result);
        $('input[name = coverBase64]').val(reader.result);
    }
    if (imgFile) {
        reader.readAsDataURL(imgFile);
    } else {
        coverImg.src = "";
    }
}
