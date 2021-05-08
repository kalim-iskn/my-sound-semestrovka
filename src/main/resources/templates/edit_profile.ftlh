<#import "/spring.ftl" as spring />
<#import "main/pjax_base.ftlh" as pjax_base />

<@pjax_base.page title="Edit profile">
    <div class="container section">
        <div class="panel col-6 mx-auto">
            <div class="content">
                <h3 class="panel-header">Edit profile</h3>
                <form method="post" class="form" enctype="multipart/form-data" action="edit-profile">
                    <#if status??>
                        <#if status>
                            <div class="alert alert-success">
                                Your profile has been successfully updated
                            </div>
                        <#else>
                            <div class="alert alert-danger">
                                An undefined error has occurred. Try again please
                            </div>
                        </#if>
                    </#if>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="form-group">
                        <label>Username*</label>
                        <@spring.formInput "editProfileForm.username" "required class='form-control'"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>
                    <div class="form-group">
                        <label>Pseudonym*</label>
                        <@spring.formInput "editProfileForm.pseudonym" "required class='form-control'"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>
                    <div class="form-group">
                        <label>Biography</label>
                        <@spring.formTextarea "editProfileForm.bio" "class='form-control'"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>
                    <div class="form-group">
                        <label>Change avatar</label>
                        <@spring.formInput "editProfileForm.avatar" "class='form-control'" "file"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>
                    <div class="form-group">
                        <label>Change cover</label>
                        <@spring.formInput "editProfileForm.cover" " class='form-control'" "file"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn main-btn">Edit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@pjax_base.page>
