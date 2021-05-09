<#import "main/pjax_base.ftlh" as pjax_base />

<#assign title>
    <#if status == 404>
        Page not found
    <#elseif status == 403>
        You do not have access to this page
    <#else>
        Something went wrong
    </#if>
</#assign>

<@pjax_base.page title=title>
    <div class="container section text-center">
        <h3>${title}</h3>
    </div>
</@pjax_base.page>