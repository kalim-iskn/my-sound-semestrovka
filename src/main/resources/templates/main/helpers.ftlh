<#macro avatarUrl avatarsDirectoryUrl noAvatarUrl avatar="">
    <#if avatar == "">${noAvatarUrl}<#else>${avatarsDirectoryUrl}/${avatar}</#if>
</#macro>