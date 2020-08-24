<#import "partials/main.ftlh" as main />
<#import "partials/pager.ftlh" as p/>
<@main.renderWith scripts = []>
    <#if role??>
        <#if role == "[ADMIN]">
            <div style="width: 20%; margin: auto">
                <a class="btn btn-primary btn-block w-100" href="/addEmployee" role="button">Добавить сотрудника</a>
            </div>
        </#if>
    </#if>
<div class="container pt-4">
    <table id="dtVerticalScroll" class="table p-2" style="width: 100%; margin-bottom: 0">
        <thead style="width: 100%">
        <tr>
            <th><a class="btn btn-info btn-block w-100" href="/">Фамилия</a></th>
            <th><a class="btn btn-info btn-block w-100" href="/name">Имя</a></th>
            <th><a class="btn btn-info btn-block w-100" href="/patronymic">Отчество</a></th>
            <th><a class="btn btn-info btn-block w-100" href="/birth">Дата рождения</a></th>
            <th><a class="btn btn-info btn-block w-100" href="/hiring">Дата принятия на работу</a></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#if employees??>
            <#list employees.content as e>
                <tr onclick="location.href='/employee/${e.id}'"
                    style="cursor: pointer;">
                    <td>${e.surname}</td>
                    <td>${e.name}</td>
                    <td>${e.patronymic}</td>
                    <td>${e.dateOfBirth}</td>
                    <td>${e.dateOfHiring}</td>
                    <td>${e.position}</td>
            <#if role??>
                <#if role == "[ADMIN]">
                    <td><a class="btn btn-warning btn-block w-100" href="/update/${e.id}" role="button">Изменить</a></td>
                    <td><a class="btn btn-danger btn-block w-100" href="/delete/${e.id}" role="button">Удалить</a>
                    <#if e.position == "WORKER">
                        <td><a class="btn btn-success btn-block w-100" href="/employee/${e.id}" role="button">Привязать</a></td>
                    </#if>
                </#if>
            </#if>
                </tr>
            </#list>
            <@p.pager url employees/>
        </#if>
        </tbody>
    </table>
</div>
</@main.renderWith>