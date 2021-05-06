<#macro avatarUrl avatar avatarsDirectoryUrl noAvatarUrl>
    <#if avatar == "">${noAvatarUrl}<#else>${avatarsDirectoryUrl}/${avatar}</#if>
</#macro>