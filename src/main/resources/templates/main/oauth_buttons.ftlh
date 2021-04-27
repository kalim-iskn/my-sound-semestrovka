<#if oauth_status??>
    <br>
    <div class="alert alert-danger">
        ${oauth_status}
    </div>
</#if>
<p>
    <a href="${vkOauthLink}" class="btn vk-btn">
        <img src="/img/vk.png">
        Sign in with VK
    </a>
</p>