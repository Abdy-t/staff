<#macro renderWith title="Staff" scripts=[] styles=[]>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

        <title>${title}</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
              integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous"/>
        <#list styles as styleSource>
            <link rel="stylesheet" href="${styleSource}"/>
        </#list>
    </head>

    <body>

        <#list scripts as scriptSource>
            <script src="${scriptSource}"></script>
        </#list>
        <div style="width: 20%; margin: auto">
            <a class="btn btn-outline-info btn-block w-100" href="/" role="button">Staff</a>
        </div>

        <div class="main container">
            <#nested>
        </div>

    </body>

    </html>

</#macro>
