<#import "main/pjax_base.ftlh" as pjax_base />

<@pjax_base.page title="Artists">
    <div class="container section">
        <div class="row">
            <#list artists as artist>
                <div class="col-lg-3 col-md-6">
                    <a href="/artist/${artist.username}" class="track-card-small" data-pjax>
                        <div class="track-cover" style="background-image: url(${artist.avatarUrl})">
                            <div class="go-to-container">
                                Go to
                            </div>
                        </div>
                        <div class="content">
                            <div class="track-name">
                                ${artist.pseudonym}
                                <#if artist.isVerified>
                                    <i class="fa fa-check-circle verified"></i>
                                </#if>
                            </div>
                            <div class="artist-name">
                                @${artist.username}
                            </div>
                        </div>
                    </a>
                </div>
            </#list>
        </div>
    </div>
</@pjax_base.page>