<#import "/spring.ftl" as spring />
<#import "../main/pjax_base.ftlh" as pjax_base />

<@pjax_base.page title=title>
    <div class="container section">
        <div class="panel col-6 mx-auto">
            <div class="cover-img" style="background-image: url('/img/new-track-cover.jpg')"></div>
            <div class="content">
                <h3 class="panel-header">${title}</h3>
                <form method="POST" class="form-group" enctype="multipart/form-data" action="${action_url}">
                    <#if status??>
                        <#if status>
                            <div class="alert alert-success">
                                <#if isAdding>
                                    Track has been successfully created
                                <#else>
                                    Track has been successfully updated
                                </#if>
                            </div>
                        <#else>
                            <div class="alert alert-danger">
                                An undefined error has occurred. Try again please
                            </div>
                        </#if>
                    </#if>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                    <div class="form-group">
                        <label>Name*</label>
                        <@spring.formInput "form.name" "required class='form-control'"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>

                    <div class="form-group">
                        <label>Description</label>
                        <@spring.formInput "form.description" "class='form-control'"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>

                    <div class="form-group">
                        <label>Poster*</label>
                        <@spring.formInput "form.poster" "required class='form-control'" "file"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>

                    <div class="form-group">
                        <label>Audio in mp3*</label>
                        <@spring.formInput "form.audio" "required class='form-control'" "file"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn main-btn">Add</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</@pjax_base.page>