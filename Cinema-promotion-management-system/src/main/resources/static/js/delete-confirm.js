document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.delete-form').forEach(function (form) {
        form.addEventListener('submit', function (event) {
            const title = form.getAttribute('data-title') || '';
            const confirmed = window.confirm(`Bạn có muốn xóa Chương trình khuyến mãi ${title}?`);
            if (!confirmed) {
                event.preventDefault();
            }
        });
    });
});
