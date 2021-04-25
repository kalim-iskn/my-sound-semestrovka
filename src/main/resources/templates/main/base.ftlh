<#macro page title>
<#if !is_pjax>
    <!doctype html>
    <html lang="en">
    <head>
        <title>${title}</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <script src="/js/pjax.js"></script>
        <script type="text/javascript" src="/js/jquery.jplayer.js"></script>
        <script type="text/javascript" src="/js/jplayer.playlist.js"></script>
    </head>
    <body>
</#if>

        <#nested/>

    <#if !is_pjax>
        <#include "audio_player.ftlh">
        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="text-center">
                            <img src="/img/logo2.png" class="logo-expand">
                            <nav class="navbar navbar-expand-sm main-page-menu">
                                <ul class="navbar nav mr-auto mx-auto">
                                    <#include "navbar_main_links.ftlh"/>
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                            Quo laborum voluptate corporis modi hic magni sed consequatur aspernatur veniam quam, debitis perspiciatis
                            voluptatibus recusandae animi voluptas est asperiores assumenda, provident.
                        </p>
                        Semestrovka 2021<br>
                        Kalimullin Iskander
                    </div>
                </div>
            </div>
        </footer>
        <div class="footer-breaks" style="display: none"></div>
        <script src="/js/audio-player.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
        <script src="/js/pjax-settings.js"></script>
        </body>
    </html>
    </#if>
</#macro>