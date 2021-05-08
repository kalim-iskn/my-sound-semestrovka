<#list tracks as track>
    <div class="col-lg-3 col-md-6">
        <div class="track-card-small">
            <a class="track-cover" href="track/${track.id}" data-pjax
               style="background-image: url(${track.posterUrl})">
                <div class="go-to-container">
                    Go to
                </div>
            </a>
            <div class="content">
                <div style="display: none" data-track-id="${track.id}" data-track-title="${track.name}"
                     data-track-artist="${track.user.pseudonym}"
                     data-track-poster="${track.posterUrl}"
                     data-track-audio="${track.audioUrl}"></div>
                <a href="" class="play-button text-center" id="initializer-${track.id}"
                   onclick="return playStopPlayer(this);">
                    <i class="fa fa-play control-audio-icon"></i>
                </a>
                <div class="track-name">
                    ${track.name}
                </div>
                <div class="artist-name">
                    ${track.user.pseudonym}
                </div>
            </div>
        </div>
    </div>
</#list>