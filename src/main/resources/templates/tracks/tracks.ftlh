<#import "../main/pjax_base.ftlh" as pjax_base />

<@pjax_base.page title="Tracks">
    <div class="container section">
        <div class="row">
            <#if tracks?? && tracks?has_content>
                <#include 'tracks_generation.ftlh' />
            <#else>
                <div class="col-lg-12 section">
                    <div class="not-found">Not found</div>
                </div>
            </#if>
        </div>
    </div>
</@pjax_base.page>