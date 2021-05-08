<#import "main/pjax_base.ftlh" as pjax_base />
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<@pjax_base.page title=info.pseudonym>
    <div class="container section">
        <#if post_status??>
            <#if post_status>
                <div class="alert alert-success">
                    Post added successfully
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
        <#if is_auth_user_profile>
            <div class="user-control-buttons">
                <a href="new_track" class="btn main-btn" data-pjax>Add new track</a>
                <a href="my_tracks" class="btn main-btn-dark" data-pjax>My tracks</a>
                <!--<a href="#" class="btn main-btn-dark" data-pjax>My Artists</a>-->
                <a href="edit-profile" class="btn main-btn-dark" data-pjax>Edit profile</a>
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
                    <h5>
                        Micro-blog
                        <#if is_auth_user_profile>
                            <a href="#" class="btn main-btn-dark new-post" data-toggle="modal" data-target="#new-post">
                                Add new post
                            </a>
                        </#if>
                    </h5>
                    <div class="micro-blog-posts">
                        <#if posts?has_content>
                            <#list posts as post>
                                <div class="micro-blog-post">
                                    <div class="author">
                                        @${info.username}
                                        <span class="date">${post.createdAt}</span>
                                    </div>
                                    <div class="text">
                                        ${post.text}
                                    </div>
                                </div>
                            </#list>
                        <#else>
                            <div class="not-found screen">Not found</div>
                        </#if>
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

    <#if is_auth_user_profile>
        <div class="modal fade" id="new-post" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Create new post</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form class="form modal-form" method="post" action="post/add" data-pjax="true" onsubmit="modalFormSubmit()">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <div class="form-group">
                                <textarea name="text" class="form-control" required></textarea>
                            </div>
                            <button type="submit" class="btn main-btn" id="post-submit">Post</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/modal-form.js"></script>
    </#if>

</@pjax_base.page>