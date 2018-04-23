var gulp = require('gulp'),
    webserver = require('gulp-webserver');

var appSrc = './programmerVisite';

gulp.task('html', function() {
    gulp.src(appSrc + '**/*.html');
});

gulp.task('css', function() {
    gulp.src(appSrc + '**/*.css');
});
gulp.task('php', function() {
    gulp.src(appSrc + '**/*.php');
});
gulp.task('js', function() {
    gulp.src(appSrc + '**/*.js');
});

gulp.task('watch', function() {
    gulp.watch(appSrc + 'css/*.css', ['css']);
    gulp.watch(appSrc + '**/*.html', ['html']);
    gulp.watch(appSrc + '**/*.js', ['js']);
    gulp.watch(appSrc + '**/*.php', ['php']);
});


gulp.task('webserver', function() {
    gulp.src(appSrc)
        .pipe(webserver({
            livereload: true,
            open: true
        }));
});
gulp.task('default', ['watch', 'webserver', 'html', 'css', 'php', 'js']);