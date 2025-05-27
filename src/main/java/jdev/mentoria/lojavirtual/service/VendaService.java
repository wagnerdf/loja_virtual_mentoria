package jdev.mentoria.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class VendaService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void exclusaoTotalVendaBanco(Long idVenda) {
		
		String value = 
		                  " BEGIN;"
		      			+ " UPDATE nota_fiscal_venda SET venda_compra_loja_virtual_id = null WHERE venda_compra_loja_virtual_id = "+idVenda+"; "
		      			+ " DELETE FROM nota_fiscal_venda WHERE venda_compra_loja_virtual_id = "+idVenda+"; "
		      			+ " DELETE FROM item_venda_loja WHERE venda_compra_loja_virtual_id = "+idVenda+"; "
		      			+ " DELETE FROM status_rastreio WHERE venda_compora_loja_virt_id = "+idVenda+"; "
		      			+ " DELETE FROM vd_cp_loja_virt WHERE id = "+idVenda+"; "
		      			+ " COMMIT; ";
		
		jdbcTemplate.execute(value);
	}
	
}
