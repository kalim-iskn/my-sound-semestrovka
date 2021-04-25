<#import "base.ftlh" as base />

<#macro page title>
    <@base.page title=title>

        <span id="pjax-container">

            <#include "navbar.ftlh"/>

            <#if is_pjax>
                <title>${title}</title>
            </#if>

            <#nested/>

        </span>

    </@base.page>
</#macro>