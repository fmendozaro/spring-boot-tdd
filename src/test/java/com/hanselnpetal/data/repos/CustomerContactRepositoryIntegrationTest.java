package com.hanselnpetal.data.repos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.hanselnpetal.domain.CustomerContact;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class CustomerContactRepositoryIntegrationTest {

	@Autowired
    private TestEntityManager entityManager;

	@Autowired
	private CustomerContactRepository customerContactRepository;

	// This tests if you can insert in the DB and it rollbacks at the end of the test
	@Test
    public void testFindByEmail() {
		
		// setup data scenario
		CustomerContact aNewContact = new CustomerContact();
		aNewContact.setEmail("fer@mail.com");
		entityManager.persist(aNewContact);
		
        // Find an inserted record using repository class
        CustomerContact foundContact = customerContactRepository.findByEmail("fer@mail.com");
        
        
        // Assertion
        assertThat(foundContact.getEmail(), is(equalTo(aNewContact.getEmail())));
    }
	
}
