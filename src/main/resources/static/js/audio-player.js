let playlistContainer = $(".jp-playlist");

$(".view-playlist").click(function () {
    if (playlistContainer.is(':visible'))
        playlistContainer.hide();
    else
        playlistContainer.show();
})

let tracks = [];
let playingNowTrackId = null;
let activeTrackInitializer = null;
let audioPlayerContainer = "#jquery_jplayer_2";

let audioPlayer = new jPlayerPlaylist({
    jPlayer: audioPlayerContainer,
    cssSelectorAncestor: "#jp_container_2"
}, [], {
    supplied: "mp3",
    wmode: "window",
    useStateClassSkin: true,
    autoBlur: false,
    smoothPlayBar: true,
    keyEnabled: true
});

$('#pjax-container').on('pjax:end', function () {
    if (playingNowTrackId !== null) {
        if ($('a').is('#initializer-' + playingNowTrackId)) {
            synchronize()
        }
    }
});

function synchronize(index = -1) {
    if (index !== -1) {
        playingNowTrackId = audioPlayer.playlist[index].id;
    }
    activeTrackInitializer = $('#initializer-' + playingNowTrackId);
    if (!$(audioPlayerContainer).data().jPlayer.status.paused || index !== -1) {
        changeIcon(activeTrackInitializer, false);
    }
}

function showPlayer() {
    $('.audio-player').show();
    $('.footer-breaks').show();
}

function getTrackInfo(trackInitializer) {
    let trackId = $(trackInitializer).attr("id").split("-")[1];
    let container = $("div[data-track-id=" + trackId + "]");
    return {
        "id": container.attr("data-track-id"),
        "title": container.attr("data-track-title"),
        "artist": container.attr("data-track-artist"),
        "poster": container.attr("data-track-poster"),
        "mp3": container.attr("data-track-audio")
    };
}

function changeIcon(trackInitializer, isSetPlay) {
    let set = "fa-pause";
    let remove = "fa-play";
    let text = "Stop";
    if (isSetPlay) {
        [set, remove] = [remove, set]
        text = "Play"
    }
    $(trackInitializer).children("i").removeClass(remove);
    $(trackInitializer).children("i").addClass(set);
    $(trackInitializer).children("span").html(text);
}

function resetIcons() {
    let icon = $(".control-audio-icon");
    icon.removeClass("fa-pause");
    icon.addClass("fa-play");
}

$(document).on("click", ".jp-play", function () {
    changeIcon(activeTrackInitializer, !$(audioPlayerContainer).data().jPlayer.status.paused);
})

function playStopPlayer(trackInitializer) {
    let trackInfo = getTrackInfo(trackInitializer);
    let id = trackInfo['id'];

    if ($(audioPlayerContainer).data().jPlayer.status.paused || id !== playingNowTrackId) {
        if (!tracks.includes(id)) {
            add_listener(id);
            tracks.push(id);
            audioPlayer.addToBeginning(trackInfo);
            audioPlayer.play(0);
            resetIcons();
        } else {
            if (id === playingNowTrackId) {
                audioPlayer.play()
            } else {
                resetIcons();
                let index = 0;
                let i = 0;
                audioPlayer.playlist.forEach(function (track) {
                    if (track.id === id)
                        index = i;
                    i++;
                })
                audioPlayer.play(index);
            }
        }
        playingNowTrackId = id;
        activeTrackInitializer = trackInitializer;
        changeIcon(trackInitializer, false);
    } else {
        audioPlayer.pause();
        changeIcon(trackInitializer, true);
    }
    showPlayer();
    return false;
}

function addToPlaylist(trackInitializer) {
    let trackInfo = getTrackInfo(trackInitializer);
    let id = trackInfo['id'];

    if (!tracks.includes(id)) {
        tracks.push(id);
        audioPlayer.add(trackInfo);
    }

    showPlayer();
    return false;
}

function add_listener(track_id) {
    $.post("/api/listeners?track_id=" + track_id);
}

function like_track(trackId, selector) {
    $.post("/api/likes?track_id=" + trackId, function () {
        $(selector).find(".text").html("Liked");
        $(selector).removeClass("main-btn");
        $(selector).addClass("btn-liked");
    });
    return false;
}
