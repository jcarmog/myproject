/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.company.projetofitness.persistencia;

import java.io.Serializable;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

/**
 *
 * @author jose
 */
@Transactional
@Interceptor
public class EntityTransactionInterceptor implements Serializable {

    private @Inject
    @Any
    Instance<EntityManager> em;

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ic) throws Exception {
        Object result = null;
        for (EntityManager emm : em) {
            boolean act = !emm.getTransaction().isActive();
            if (act) {
                emm.getTransaction().begin();
            }
            try {
                result = ic.proceed();
                if (act) {
                    emm.getTransaction().commit();
                }
                return result;
            } catch (Exception e) {
                if (act) {
                    emm.getTransaction().rollback();
                }
                throw e;
            }
        }
        return result; 
    }
}
