<#import "partials/main.ftlh" as main />
<@main.renderWith scripts = []>
<div class="container pt-4">
    <#if employee??>
            <p>Должность: <b style="color: #0f6674">${employee.position}</b></p>
            <p>Фамилия: <b style="color: #0f6674">${employee.surname}</b></p>
            <p>Имя: <b style="color: #0f6674">${employee.name}</b></p>
            <p>Отчество: <b style="color: #0f6674">${employee.patronymic}</b></p>
            <p>Дата рождения: <b style="color: #0f6674">${employee.dateOfBirth}</b></p>
            <p>Дата принятия на работу: <b style="color: #0f6674">${employee.dateOfHiring}</b></p>
            <#if manager??>
                    <p>Привязан к: <a href="/employee/${manager.id}"><b style="color: #0f6674">${manager.surname} ${manager.name}</b></a></p>
            </#if>
            <#if managers??>
                    <div class="container pt-4">
                            <h5>Список менеджеров</h5>
                            <table id="dtVerticalScroll" class="table p-2" style="width: 100%; margin-bottom: 0">
                                    <thead style="width: 100%">
                                    <tr>
                                            <th>Фамилия</th>
                                            <th>Имя</th>
                                            <th>Отчество</th>
                                            <th>Дата рождения</th>
                                            <th>Дата принятия на работу</th>
                                            <th>Должность</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list managers as m>
                                            <tr onclick="location.href='/employee/${m.id}'"
                                                style="cursor: pointer;">
                                                    <td>${m.surname}</td>
                                                    <td>${m.name}</td>
                                                    <td>${m.patronymic}</td>
                                                    <td>${m.dateOfBirth}</td>
                                                    <td>${m.dateOfHiring}</td>
                                                    <td>${m.position}</td>
                                            <#if role??>
                                                    <#if role == "[ADMIN]">
                                                        <td><a class="btn btn-success btn-block w-100" href="/relation/${employee.id}/${m.id}" role="button">Привязать</a></td>
                                                    </#if>
                                            </#if>
                                            </tr>
                                    </#list>
                                    </tbody>
                            </table>
                    </div>
            </#if>
            <#if workers??>
                    <div class="container pt-4">
                            <h5>Привязанные сотрудники</h5>
                            <table id="dtVerticalScroll" class="table p-2" style="width: 100%; margin-bottom: 0">
                                    <thead style="width: 100%">
                                    <tr>
                                            <th>Фамилия</th>
                                            <th>Имя</th>
                                            <th>Отчество</th>
                                            <th>Дата рождения</th>
                                            <th>Дата принятия на работу</th>
                                            <th>Должность</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list workers as w>
                                            <tr onclick="location.href='/employee/${w.id}'"
                                                style="cursor: pointer;">
                                                    <td>${w.surname}</td>
                                                    <td>${w.name}</td>
                                                    <td>${w.patronymic}</td>
                                                    <td>${w.dateOfBirth}</td>
                                                    <td>${w.dateOfHiring}</td>
                                                    <td>${w.position}</td>
                                            </tr>
                                    </#list>
                                    </tbody>
                            </table>
                    </div>
            </#if>
    <#else>
        <h3>Сотрудник не найден.</h3>
    </#if>
</div>
</@main.renderWith>
<#import "partials/main.ftlh" as main />
