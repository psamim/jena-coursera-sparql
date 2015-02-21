<#assign
current = url + "?name=" + query.name[0]
>  

See the results graph in
<a href="${current}&type=TTL">TTL</a>
or
<a href="${current}&type=">XML</a>
format, or in a
<a href="${current}&type=table">table</a>.


<#if query.type[0] == "table">
    <table class="table table-hover">
        <tr><th>Name<th></tr>
            <#list results as row>
                <tr>
                    <td>${row.name}<td>
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
