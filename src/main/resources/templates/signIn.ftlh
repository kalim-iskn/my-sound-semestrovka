<#import "/spring.ftl" as spring />
<#import "main/pjax_base.ftlh" as pjax_base />

<@pjax_base.page title="Sign In">
    <div class="container section">
        <div class="panel col-6 mx-auto">
            <div class="content">
                <h3 class="panel-header">Sign in</h3>
                <form method="post" class="form" enctype="multipart/form-data" action="/signin">
                    <#if invalid_credentials??>
                        <div class="alert alert-danger">
                            You entered an incorrect username or password
                        </div>
                    </#if>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="form-group">
                        <label>Username</label>
                        <input type="text" name="username" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="password" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn main-btn">Register</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@pjax_base.page>
