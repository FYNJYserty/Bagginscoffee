function toggleTheme() {
    var body = document.forms;
    var themeIcon = document.querySelector('.theme-icon');
    body.classList.toggle('dark-theme');
    if (body.classList.contains('dark-theme')) {
        themeIcon.src = 'dark-theme-icon.png';
    } else {
        themeIcon.src = 'light-theme-icon.png';
    }
}