<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<@security.authorize access="isAuthenticated()">
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
           aria-haspopup="true" aria-expanded="false">
            @<@security.authentication property="principal.username" />
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <a class="dropdown-item" href="my_profile" data-pjax>My profile</a>
            <a class="dropdown-item" href="my_tracks" data-pjax>My tracks</a>
            <a class="dropdown-item" style="background: #423c3c; color: white" href="new_track" data-pjax>
                Add new track
            </a>
        </div>
    </li>
</@security.authorize>

<@security.authorize access="!isAuthenticated()">
    <li class="nav-item">
        <a href="signin" class="nav-link" data-pjax>Sign in</a>
    </li>
</@security.authorize>
<li class="nav-item">
    <@security.authorize access="isAuthenticated()">
        <a href="logout" class="nav-link" data-pjax>Log out</a>
    </@security.authorize>
    <@security.authorize access="!isAuthenticated()">
        <a href="signup" class="nav-link" data-pjax>Sign up</a>
    </@security.authorize>
</li>