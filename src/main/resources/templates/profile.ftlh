<#import "main/pjax_base.ftlh" as pjax_base />
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<@pjax_base.page title=info.pseudonym>
    <div class="container section">
        <#if is_auth_user_profile>
            <div class="user-control-buttons">
                <a href="/new_track" class="btn main-btn" data-pjax>Add new track</a>
                <a href="/my_tracks" class="btn main-btn-dark" data-pjax>My tracks</a>
                <a href="#" class="btn main-btn-dark" data-pjax>My Artists</a>
                <a href="/edit-profile" class="btn main-btn-dark" data-pjax>Edit profile</a>
            </div>
        </#if>
        <div class="profile-info white-content">
            <#if info.cover??>
                <div class="profile-cover" style="background-image: url(${info.coverUrl})"></div>
            </#if>
            <div class="row content">
                <div class="col-md-3">
                    <#if info.avatarUrl??>
                        <div class="profile-avatar" style="background-image: url(${info.avatarUrl})"></div>
                    </#if>
                    <p class="statistic">
                        <a href="#">Tracks</a>: <#if statistic.tracks??>${statistic.tracks}</#if><br>
                        <a href="#">Listens</a>: <#if statistic.listens??>${statistic.listens}<#else>0</#if><br>
                        <a href="#">Likes</a>: <#if statistic.likes??>${statistic.likes}<#else>0</#if>
                    </p>
                </div>
                <div class="col-md-5">
                    <h3>
                        ${info.pseudonym}
                        <#if info.isVerified>
                            <i class="fa fa-check-circle verified" aria-hidden="true"></i>
                        </#if>
                    </h3>
                    <#if info.bio??>
                        <p>
                            <h5>Info</h5>
                            <div>${info.bio}</div>
                        </p>
                    </#if>
                    <#if !is_auth_user_profile>
                        <!--<a href="#" class="btn main-btn">Follow</a>-->
                    </#if>
                </div>
                <div class="col-md-4">
                    <h5>Micro-blog</h5>
                    <div class="micro-blog-posts">
                        <div class="micro-blog-post">
                            <div class="author">
                                @${info.username}
                                <span class="date">14.03.21</span>
                            </div>
                            <div class="text">
                                I didn't have an account in mysound. It's not me. I'm being held hostage. Help me
                            </div>
                        </div>
                        <div class="micro-blog-post">
                            <div class="author">
                                @${info.username}
                                <span class="date">14.03.21</span>
                            </div>
                            <div class="text">
                                I didn't have an account in mysound. It's not me. I'm being held hostage. Help me
                            </div>
                        </div>
                        <div class="micro-blog-post">
                            <div class="author">
                                @${info.username}
                                <span class="date">14.03.21</span>
                            </div>
                            <div class="text">
                                I didn't have an account in mysound. It's not me. I'm being held hostage. Help me
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="section profile-tracks white-content">
            <h3 style="color: #D32F2F">Tracks</h3>
            <div class="row">
                <#include "tracks/tracks_generation.ftlh">
            </div>
        </div>
    </div>
</@pjax_base.page>