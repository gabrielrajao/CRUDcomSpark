package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Planta;

public class PlantaDAO {
	private List<Planta> produtos;
	private int maxId = 0;

	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public int getMaxId() {
		return maxId;
	}

	public PlantaDAO(String filename) throws IOException {
		file = new File(filename);
		produtos = new ArrayList<Planta>();
		if (file.exists()) {
			readFromFile();
		}

	}

	public void add(Planta produto) {
		try {
			produtos.add(produto);
			this.maxId = (produto.getId() > this.maxId) ? produto.getId() : this.maxId;
			this.saveToFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar a planta '" + produto.getNome() + "' no disco!");
		}
	}

	public Planta get(int id) {
		for (Planta produto : produtos) {
			if (id == produto.getId()) {
				return produto;
			}
		}
		return null;
	}

	public void update(Planta p) {
		int index = produtos.indexOf(p);
		if (index != -1) {
			produtos.set(index, p);
			this.saveToFile();
		}
	}

	public void remove(Planta p) {
		int index = produtos.indexOf(p);
		if (index != -1) {
			produtos.remove(index);
			this.saveToFile();
		}
	}

	public List<Planta> getAll() {
		return produtos;
	}

	private List<Planta> readFromFile() {
		produtos.clear();
		Planta produto = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				produto = (Planta) inputFile.readObject();
				produtos.add(produto);
				maxId = (produto.getId() > maxId) ? produto.getId() : maxId;
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler planta do disco!");
			e.printStackTrace();
		}
		return produtos;
	}

	private void saveToFile() {
		try {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Planta produto : produtos) {
				outputFile.writeObject(produto);
			}
			outputFile.flush();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar planta no disco!");
			e.printStackTrace();
		}
	}

	private void close() throws IOException {
		outputFile.close();
		fos.close();
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			this.saveToFile();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao salvar a base de dados no disco!");
			e.printStackTrace();
		}
	}
}
