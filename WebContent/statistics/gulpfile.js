var gulp = require('gulp'),
    webserver = require('gulp-webserver');

var appSrc = './';
//var appSrc = './backend';

gulp.task('html', function () {
    gulp.src(appSrc + '**/*.html');
});

gulp.task('css', function () {
    gulp.src(appSrc + '**/*.css');
});

gulp.task('watch', function () {
    gulp.watch(appSrc + 'css/*.css', ['css']);
    gulp.watch(appSrc + '**/*.html', ['html']);
});


gulp.task('webserver', function () {
    gulp.src(appSrc)
        .pipe(webserver({
            livereload: true,
            open: true
        }));
});
gulp.task('default', ['watch', 'webserver', 'html', 'css']);