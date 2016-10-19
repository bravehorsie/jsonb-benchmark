package org.eclipselink.jsonb.tests;

import org.eclipselink.jsonb.payload.Address;
import org.eclipselink.jsonb.payload.Customer;
import org.eclipselink.jsonb.payload.Street;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Roman Grigoriadi
 */
public class AbstractCustomerTest {

    protected Customer createCustomer(String customerName) {
        Street street = new Street("Zoubkova", 111);
        Address address = new Address(street, "Prague");
        Customer customer = new Customer(33, customerName);

        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        customer.setAddresses(addresses);

        List<String> strings = new ArrayList<>();
        strings.add("green");
        strings.add("yellow");
        customer.setStrings(strings);

        List<Integer> integers = new ArrayList<>();
        integers.add(0);
        integers.add(1);
        customer.setIntegers(integers);

        Map<String, Integer> stringIntegerMap = new HashMap<>();
        stringIntegerMap.put("first", 1);
        stringIntegerMap.put("second", 2);
        customer.setStringIntegerMap(stringIntegerMap);

        List<List<Integer>> listOfListsOfIntegers = new ArrayList<>();
        for(int i=0; i<3; i++) {
            List<Integer> integerList = new ArrayList<>();
            integerList.add(0);
            integerList.add(1);
            integerList.add(2);
            listOfListsOfIntegers.add(integerList);
        }

        customer.setListOfListsOfIntegers(listOfListsOfIntegers);

        return customer;
    }
}
