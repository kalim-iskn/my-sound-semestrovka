<#import "../main/pjax_base.ftlh" as pjax_base />

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
                        <i class="fa fa-comment"></i> 256
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
            <div class="col-md-12 section">
                <h3>Comments</h3>
                <form class="form section">
                    <div class="form-group">
                        <textarea placeholder="Your comment" class="form-control"></textarea>
                    </div>
                    <div class="text-right">
                        <input type="submit" class="btn " value="Publish">
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 section">
                <div class="comment">
                    <div class="avatar-mini"
                         style="background-image: url(https://i1.sndcdn.com/artworks-000233579913-umyfy6-t500x500.jpg)"></div>
                    <div class="info">
                        kalim.iskn
                        <span class="date">18.03.2020</span>
                    </div>
                    <div class="text">
                        <div>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                            Ad aut dicta dolor, doloribus eius esse exercitationem facere laborum,
                            molestias nostrum nulla perferendis quae quibusdam quod ratione recusandae,
                            sapiente sequi voluptate!
                        </div>
                        <div>
                            Alias aperiam assumenda aut consequatur cumque distinctio eligendi error expedita harum
                            impedit, ipsum iure molestiae natus necessitatibus nihil nostrum numquam, obcaecati officiis
                            quae repudiandae saepe sit ullam veniam? Amet, nisi?
                        </div>
                    </div>
                </div>
                <div class="comment">
                    <div class="avatar-mini"
                         style="background-image: url(https://i1.sndcdn.com/artworks-000233579913-umyfy6-t500x500.jpg)"></div>
                    <div class="info">
                        kalim.iskn
                        <span class="date">18.03.2020</span>
                    </div>
                    <div class="text">
                        <div>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                            Ad aut dicta dolor, doloribus eius esse exercitationem facere laborum,
                            molestias nostrum nulla perferendis quae quibusdam quod ratione recusandae,
                            sapiente sequi voluptate!
                        </div>
                        <div>
                            Alias aperiam assumenda aut consequatur cumque distinctio eligendi error expedita harum
                            impedit, ipsum iure molestiae natus necessitatibus nihil nostrum numquam, obcaecati officiis
                            quae repudiandae saepe sit ullam veniam? Amet, nisi?
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@pjax_base.page>