<#import "/spring.ftl" as spring />
<#import "main/pjax_base.ftlh" as pjax_base />

<@pjax_base.page title="Sign Up">
    <div class="container section">
        <div class="panel col-6 mx-auto">
            <div class="content">
                <h3 class="panel-header">Sign up</h3>
                <form method="post" class="form" enctype="multipart/form-data" action="/signup">
                    <#if success??>
                        <div class="alert alert-success">
                            You have registered successfully.<br>
                            <a href="/signin" data-pjax>Sign in</a>
                        </div>
                    </#if>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="form-group">
                        <label>Username</label>
                        <@spring.formInput "signUpForm.username" "required class='form-control'"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>
                    <div class="form-group">
                        <label>Pseudonym</label>
                        <@spring.formInput "signUpForm.pseudonym" "required class='form-control'"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>
                    <div class="form-group">
                        <label>Biography</label>
                        <@spring.formInput "signUpForm.bio" "required class='form-control'"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <@spring.formInput "signUpForm.email" "required class='form-control'" "email"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <@spring.formPasswordInput "signUpForm.password" "required class='form-control'"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>
                    <div class="form-group">
                        <label>Repeat password</label>
                        <@spring.formPasswordInput "signUpForm.rePassword" "required class='form-control'"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>
                    <div class="form-group">
                        <label>Avatar</label>
                        <@spring.formInput "signUpForm.avatar" "required class='form-control'" "file"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>
                    <div class="form-group">
                        <label>Cover</label>
                        <@spring.formInput "signUpForm.cover" " class='form-control'" "file"/>
                        <@spring.showErrors "<br>" "invalid-feedback"/>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn main-btn">Register</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@pjax_base.page>
