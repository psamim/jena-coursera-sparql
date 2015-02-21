<#assign
current = url + "?name=" + query.name[0]
>  

<div class="row">
    <div class="col-md-12">
        <div class="panel-body">
            <h1 class="text-center">Search Results</h1>
            <p class="bg-success semantic-messages">
                See the graph in
                <a href="${current}&type=TTL">TTL</a>
                or
                <a href="${current}&type=">XML</a>
                format, or in a
                <a href="${current}&type=table">table</a>.
            </p>

            <#if query.type[0] == "table">
                <table class="table table-hover">
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Instructors</th>
                        <th>University</th>
                    </tr>
                    <#list results as row>
                        <tr>
                            <td>
                                ${row_index + 1}
                            </td>
                            <td>
                                <a href="${row.sessions[0].homepage}" target="_blank">
                                    ${row.courseName}
                                </a>
                            </td>
                            <td>
                                <#list row.instructors as person>
                                    <a href="">
                                        ${person.firstname} ${person.lastname}
                                    </a>
                                    <#if person_has_next>,</#if>
                                </#list>
                            </td>
                            <td>
                                <#list row.universities as uni>
                                    ${uni.shortName}
                                    <#if uni_has_next>,</#if>
                                </#list>
                            </td>
                        </tr>
                    </#list>
                </table>  
            <#else>
                <pre>
                    <code class="xml">
                        ${results}
                    </code>
                </pre>
            </#if>  

        </div>
    </div>
</div>
