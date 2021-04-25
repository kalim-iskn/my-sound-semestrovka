<#import "main/base.ftlh" as base />

<@base.page title="Mysound - Home">

    <#if is_pjax>
        <title>Mysound - Home</title>
    </#if>

    <span id="pjax-container">
        <div class="main-block h-100 d-flex align-items-center justify-content-center">
            <div class="main-search-block">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <img src="/img/logo2.png">
                        <nav class="navbar navbar-expand-sm main-page-menu">
                            <ul class="navbar nav mr-auto">
                                <#include "main/navbar_main_links.ftlh">
                            </ul>
                            <ul class="navbar nav ml-auto">
                                <#include "main/navbar_user_links.ftlh">
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="search-slogan">
                            Find your
                            <span class="highlighting-word">sound</span>
                        </div>
                        <form class="form" style="margin-top: 10px">
                            <div class="form-group">
                                <input type="text" class="form-control"
                                       style="padding: 10px"
                                       placeholder="Artist, band or track">
                            </div>
                            <div class="text-right">
                                <input type="submit"
                                       class="btn btn-primary main-btn"
                                       value="Search">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="container screen">
            <h1 class="text-center">Popular tracks</h1>
            <div class="row section">
                {% for track in tracks %}
                    <div class="col-lg-3 col-md-6">
                        <div class="track-card-small">
                            <a href="{% url 'track_page' slug=track.slug %}" data-pjax
                               class="track-cover"
                               style="background-image: url({% get_media_prefix %}{{ track.poster }})">
                                <div class="go-to-container">
                                    Go to
                                </div>
                            </a>
                            <div class="content">
                                <a href="" class="play-button text-center" id="initializer-{{ track.id }}"
                                   onclick="return playStopPlayer(this);"
                                   data-track-id="{{ track.id }}" data-track-title="{{ track.name }}"
                                   data-track-artist="{{ info.pseudonym }}"
                                   data-track-poster="{% get_media_prefix %}{{ track.poster }}"
                                   data-track-audio="{% get_media_prefix %}{{ track.audio }}">
                                    <i class="fa fa-play control-audio-icon"></i>
                                </a>
                                <div class="track-name">
                                    {{ track.name }}
                                </div>
                                <div class="artist-name">
                                    {{ track.user__pseudonym }}
                                </div>
                            </div>
                        </div>
                    </div>
                {% endfor %}
            </div>
        </div>
    </span>
</@base.page>