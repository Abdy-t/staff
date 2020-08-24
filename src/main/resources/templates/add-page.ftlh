<#import "partials/main.ftlh" as main />
<@main.renderWith scripts = []>
    <style>
        input {
            margin: 10px;
            width: 300px;
        }
        p {
            margin: auto;
            width: 50%;
            text-align: center;
        }
    </style>
        <div class="container pt-4">
            <div style="width: 30%; margin: auto">
                <#if errors??>
                        <#list errors as fieldError>
                                <strong><p style="color:#fd7777">${fieldError.defaultMessage!'-- no message --'}</p></strong>
                        </#list>
                </#if>
                    <form action="/addEmployee" method="post">
                            <#if _csrf??>
                                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                            </#if>
                            <input type="text" name="surname" value="${form.surname!''}" minlength="2" placeholder="Фамилия" required="required">
                            <input type="text" name="name" value="${form.name!''}" minlength="2" placeholder="Имя" required="required">
                            <input type="text" name="patronymic" value="${form.patronymic!''}" minlength="2" placeholder="Отчество">
                            <#if errorInputDate??> <p style="color: #ba8b00">${errorInputDate}</p></#if>
                            <#if errorDate??> <p style="color: #ba8b00">${errorDate}</p></#if>
                            <#if personMature??> <p style="color: #ba8b00">${personMature}</p></#if>
                            <input type="date" name="dateOfBirth" min="1940-01-01" max="2020-01-01" value="${form.dateOfBirth!''}" placeholder="Дата рождения" required="required">
                            <input type="date" name="dateOfHiring" min="1960-01-01" value="${form.dateOfHiring!''}" placeholder="Дата принятия на работу" required="required">
                            <input type="radio" name="position" value="MANAGER" required="required"> <p>MANAGER</p>
                            <input type="radio" name="position" value="WORKER" required="required" checked="checked"> <p>WORKER</p>
                            <input type="submit">
                    </form>
            </div>
        </div>
</@main.renderWith>