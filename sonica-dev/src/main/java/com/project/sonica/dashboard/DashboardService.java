package com.project.sonica.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.sonica.repos.BookingRepository;
import com.project.sonica.repos.PaymentRepository;
import com.project.sonica.security.Role;
import com.project.sonica.security.User;
import com.project.sonica.security.UserRepository;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;

    public DashboardService(UserRepository userRepository,
                            BookingRepository bookingRepository,
                            PaymentRepository paymentRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
    }

    // Admin dashboard data
    public Map<String, Object> getAdminData() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalUsers", userRepository.count());
        data.put("activePhotographers", userRepository.countByRole(Role.PHOTOGRAPHER));
        data.put("pendingPayments", paymentRepository.countByStatus("PENDING"));
        return data;
    }

    // Photographer dashboard data
    public Map<String, Object> getPhotographerData(String photographerName) {
        Map<String, Object> data = new HashMap<>();
        data.put("upcomingShoots", bookingRepository.findByPhotographerName(photographerName));
        data.put("pendingApprovals", paymentRepository.countByStatus("PENDING"));
        return data;
    }

    // Customer dashboard data
    public Map<String, Object> getCustomerData(String customerName) {
        Map<String, Object> data = new HashMap<>();
        data.put("bookings", bookingRepository.findByCustomerName(customerName));
        data.put("availablePhotographers", userRepository.findAll()
                .stream()
                .filter(u -> u.getRole() == Role.PHOTOGRAPHER)
                .map(User::getUsername)
                .toList());
        return data;
    }
}
