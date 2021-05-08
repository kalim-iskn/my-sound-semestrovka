<#import "/spring.ftl" as spring />
<#import "../main/pjax_base.ftlh" as pjax_base />

<@pjax_base.page title="My tracks">
    <div class="container section">
        <div class="panel col-8 mx-auto">
            <div class="cover-img" style="background-image: url('${springMacroRequestContext.contextPath}/img/new-track-cover.jpg')"></div>
            <div class="content">
                <h3 class="panel-header">My tracks</h3>
                <#if isDeleted??>
                    <#if isDeleted>
                        <div class="alert alert-success">
                            Track has been successful deleted
                        </div>
                    <#else>
                        <div class="alert alert-danger">
                            Undefined problem. Try again please
                        </div>
                    </#if>
                </#if>
                <div class="section tracks-list">
                    <#list tracks as track>
                        <div class="row">
                            <div class="col-md-3 text-center">
                                <a href="track/${track.id}" data-pjax>
                                    <img src="${track.posterUrl}" class="poster">
                                </a>
                                <div class="statistic">
                                    <i class="fa fa-heart"></i> ${track.numberOfLikes}
                                    <i class="fa fa-play"></i> ${track.numberOfListens}
                                    <i class="fa fa-comment"></i> ${track.numberOfComments}
                                </div>
                            </div>
                            <div class="col-md-7">
                                <h4>${track.name}</h4>
                                <p>
                                    ${track.description}
                                </p>
                            </div>
                            <div class="col-md-2 links">
                                <a href="edit-track/${track.id}" class="btn main-btn-dark" data-pjax>
                                    Edit <i class="fa fa-edit"></i>
                                </a>
                                <a href="delete-track/${track.id}" class="btn main-btn" data-pjax>
                                    Delete <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>
</@pjax_base.page>