<#import "../main/pjax_base.ftlh" as pjax_base />
<#import "../main/helpers.ftlh" as helpers />

<@pjax_base.page title=track.name>
    <div class="container section">
        <div class="row">
            <div class="col-md-4 track-poster-container">
                <img src="${track.posterUrl}" style="width: 100%">
                <div class="track-statistic">
                    <div class="parameter">
                        <i class="fa fa-heart"></i> ${track.numberOfLikes}
                    </div>
                    <div class="parameter">
                        <i class="fa fa-play"></i> ${track.numberOfListens}
                    </div>
                    <div class="parameter" style="margin: 0">
                        <i class="fa fa-comment"></i> ${track.numberOfComments}
                    </div>
                </div>
            </div>
            <div class="col-md-8 track-info">
                <h1>${track.name}</h1>
                <h5 style="color: #D32F2F">
                    <a href="/artist/${track.user.username}" class="inherit-link" data-pjax>
                        ${track.user.pseudonym}
                    </a>
                    <#if track.user.isVerified>
                        <i class="fa fa-check-circle verified" aria-hidden="true"></i>
                    </#if>
                </h5>
                <div class="description">
                    <#if track.description??>
                        <div>
                            ${track.description}
                        </div>
                    </#if>
                    <div class="added-date">
                        Added on ${track.createdAt}
                    </div>
                    <div style="display: none" data-track-id="${track.id}" data-track-title="${track.name}"
                         data-track-artist="${track.user.pseudonym}" data-track-poster="${track.posterUrl}"
                         data-track-audio="${track.audioUrl}"></div>
                    <a href="" class="btn main-btn-dark" id="initializer-${track.id}"
                       onclick="return playStopPlayer(this);">
                        <i class="fa fa-play control-audio-icon"></i> <span>Play</span>
                    </a>

                    <#if isAuthenticated>
                        <#if isLiked>
                            <a href="#" class="btn btn-liked">
                                <i class="fa fa-heart"></i> Liked
                            </a>
                        <#else>
                            <a href="" class="btn main-btn" onclick="return like_track(${track.id}, this)"
                               id="like-btn">
                                <i class="fa fa-heart"></i> <span class="text">Like</span>
                            </a>
                        </#if>
                    </#if>

                    <a href="" class="btn btn-basic" id="addtrack-${track.id}" onclick="return addToPlaylist(this)">
                        <i class="fa fa-list"></i> Add to list
                    </a>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 screen">
                <h3>Comments</h3>
                <#if isAuthenticated>
                    <form class="form section" method="post" action="/comment/add?track_id=${track.id}">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <#if comment_status??>
                            <#if comment_status>
                                <div class="alert alert-success">
                                    Comment added successfully
                                </div>
                            <#else>
                                <div class="alert alert-danger">
                                    An undefined error has occurred. Try again please
                                </div>
                            </#if>
                        </#if>
                        <#if text_invalid??>
                            <div class="alert alert-danger">
                                ${text_invalid}
                            </div>
                        </#if>
                        <div class="form-group">
                            <textarea placeholder="Your comment" name="text" class="form-control" required></textarea>
                        </div>
                        <div class="text-right">
                            <input type="submit" class="btn " value="Publish">
                        </div>
                    </form>
                </#if>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <#list comments as comment>

                    <#assign avatar>
                        <@helpers.avatarUrl avatarsDirectoryUrl noAvatarUrl comment.user.avatar></@helpers.avatarUrl>
                    </#assign>

                    <div class="comment">
                        <div class="avatar-mini"
                             style="background-image: url(${avatar})"></div>
                        <div class="info">
                            <a href="/artist/${comment.user.username}" style="color: inherit" data-pjax>${comment.user.username}<#if comment.user.isVerified><i class="fa fa-check-circle verified"></i></#if></a>
                            <span class="date">${comment.createdAt}</span>
                        </div>
                        <div class="text">
                            ${comment.text}
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</@pjax_base.page>