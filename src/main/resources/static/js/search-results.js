$(document).ready(function () {
    $('.clickable-li').on('click', function () {
        const verseId = $(this).data('id');
        window.location.href = '/bible-verse/view/' + verseId;
    });

    const keyword = $('input[name="keyword"]').val();
    if (keyword) {
        $(".highlightable-keyword").each(function () {
            const text = $(this).text();
            const highlightedText = text.replace(new RegExp(`(${keyword})`, "gi"), "<mark>$1</mark>");
            $(this).html(highlightedText);
        });
    }
});